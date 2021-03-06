package com.bookshop.productms.service.impl;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.productms.config.MinioConf;
import com.bookshop.productms.persistence.IProductRepository;
import com.bookshop.productms.persistence.model.Product;
import com.bookshop.productms.service.IProductService;
import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;

@Service
public class ProductService implements IProductService {
	
	@Autowired
    private MinioService minioService;
	
	@Autowired
    private MinioConf minioConf;
	
	@Autowired
	private IProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(String id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product createProduct(Product product) {
		product.setId(UUID.randomUUID().toString());
		product.setPicUrl(this.saveProductPic(product.getPicUrl()));
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(String id) {
		productRepository.deleteById(id);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}
	
	private String saveProductPic(String imageAddress) {
		try {
        	String fileName = Paths.get(new URI(imageAddress).getPath()).getFileName().toString();
        			
        	Path path = Paths.get(fileName);
        	
        	CloseableHttpClient client = HttpClientBuilder.create().build();
        	HttpGet request = new HttpGet(imageAddress);
        	HttpResponse response = client.execute(request);
        	HttpEntity entity = response.getEntity();
        	
        	InputStream inputStream = entity.getContent();
        	
        	String filePath = fileName;
        	FileOutputStream fos = new FileOutputStream(filePath);
        	int fileByte;
        	while((fileByte = inputStream.read()) != -1) {
        	    fos.write(fileByte);
        	}
        	fos.flush();
        	fos.close();
        	
        	final File initialFile = new File(filePath);
            final InputStream targetStream = 
              new DataInputStream(new FileInputStream(initialFile));
        	
            String mimeType = Files.probeContentType(initialFile.toPath());
            
            minioService.upload(path, targetStream,  mimeType);
            
            if (initialFile.exists() && initialFile.isFile()) {
            	initialFile.delete();
            }
            
            return minioConf.getBucket() + "/" + fileName;
            
        } catch (URISyntaxException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        } catch (MinioException e) {
            throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        } catch (IOException e) {
            throw new IllegalStateException("The file cannot be read", e);
        }
	}
}

FROM node:lts-alpine
RUN npm install -g http-server

WORKDIR /app

COPY dist ./dist

EXPOSE 8080
CMD [ "http-server", "dist" ]
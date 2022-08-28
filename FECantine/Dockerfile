#stage 1
FROM node:latest as node
WORKDIR /FECantine
COPY . .
RUN npm install
RUN npm run build --prod
#stage 2
FROM nginx:alpine
COPY --from=node /FECantine/dist/fecantine /usr/share/nginx/html
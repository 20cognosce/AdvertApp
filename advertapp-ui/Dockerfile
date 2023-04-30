FROM node:18.14.0

WORKDIR /home/advertapp-ui
COPY . .
RUN npm ci
RUN npm install serve
RUN npm run build
ENV NODE_ENV production
EXPOSE 3000
CMD [ "npx", "serve", "build" ]
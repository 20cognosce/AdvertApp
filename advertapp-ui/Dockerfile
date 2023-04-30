FROM node:18.14.0

WORKDIR /home/advertapp-ui
COPY . .
RUN npm ci
RUN npm install -g serve

ARG BACKEND_BASE_URI
ENV REACT_APP_BACKEND_BASE_URI $BACKEND_BASE_URI
ENV NODE_ENV production

RUN npm run build

EXPOSE 3000
CMD [ "npx", "serve", "build" ]
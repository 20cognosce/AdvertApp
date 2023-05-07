FROM node:18.14.0

WORKDIR /home/advertapp-ui
COPY . .
RUN npm ci

ENV NODE_ENV production

EXPOSE 3000
CMD [ "npm", "start"]
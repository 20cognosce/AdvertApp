FROM node:14.18.2
WORKDIR /advertapp-ui
COPY  package.json ./

RUN npm install

COPY . /advertapp-ui
ENV port=3000
EXPOSE 3000

CMD ["npm", "start"]
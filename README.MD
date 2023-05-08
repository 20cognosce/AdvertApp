<summary>Application pre-setup</summary>

    1. Set HOST_IP env variable (your host machine external IP)
       in .env file in current directory

    HOST_IP=192.168.000.000

    2. Assure that the current profile is prod in application.yml 

<summary>Install docker on your system</summary>
    <br>
<summary>Install docker-compose</summary>
    <br>

<summary>verify installation:</summary>

```bash
docker -v
docker-compose --version
```

<summary>in the project directory run:</summary>

```bash
docker-compose up
```
<summary>Application pre-setup</summary>

    Copy your host IPv4 address
    
    Windows: ipconfig
    Linux: hostname -I

    Go to docker-compose.yml and put it in the variable:
    BACKEND_BASE_URI: http://{YOUR_HOST_IP}:8080

    Also assure that in application.yml current profile is prod

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
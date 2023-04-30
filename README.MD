<summary>Application pre-setup</summary>

    Copy your host IPv4 address
    
    Windows: ipconfig
    Linux: hostname -I

    Go to docker-compose.yml and put it in the variable:
    BACKEND_BASE_URI: http://{YOUR_HOST_IP}:8080

    Also assure that in application.yml current profile is prod


Install docker on your system
<br>
Install docker-compose

verify installation:
```bash
docker -v
docker-compose --version
```
in the project directory run:
```bash
docker-compose up
```
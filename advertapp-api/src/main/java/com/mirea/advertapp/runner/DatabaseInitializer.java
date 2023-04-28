package com.mirea.advertapp.runner;

import com.mirea.advertapp.domain.dto.AdvertCreateDto;
import com.mirea.advertapp.domain.entity.Address;
import com.mirea.advertapp.domain.entity.Advert;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.domain.entityenum.Role;
import com.mirea.advertapp.domain.entityenum.UserAccountStatus;
import com.mirea.advertapp.service.AdvertService;
import com.mirea.advertapp.service.ImageService;
import com.mirea.advertapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final AdvertService advertService;
    private final ImageService imageService;

    private Path projectPath;

    private static final String ADMIN_EMAIL = "admin";
    private static final String USER_EMAIL = "user@mail.ru";

    @Autowired
    public void setProjectPath(@Value("${project-dir}") Path projectPath) {
        this.projectPath = projectPath;
    }

    @Override
    public void run(String... args) {
        if (userService.getByEmailOptional(ADMIN_EMAIL).isPresent() &&
                userService.getByEmailOptional(USER_EMAIL).isPresent()) {
            return;
        }
        getUsers().forEach(userService::create);
        log.info("Database init: users created");
        getAdverts().forEach(advertService::create);
        log.info("Database init: adverts created");
        loadImages();
        log.info("Database init: images created");
    }

    private List<User> getUsers() {
        return Arrays.asList(
                new User(null, "Admin", "", null, ADMIN_EMAIL, userService.encodePassword("password".toCharArray()), Role.ADMIN, UserAccountStatus.ACTIVE, null),
                new User(null, "User", "", null, USER_EMAIL, userService.encodePassword("password".toCharArray()), Role.USER, UserAccountStatus.ACTIVE, null)
        );
    }

    private List<AdvertCreateDto> getAdverts() {
        User user = userService.getByEmail(USER_EMAIL);

        var address1 = Address.builder()
                .id(null)
                .county("Московская область")
                .city("Москва")
                .district("Дорогомилово")
                .street("Студенческая")
                .houseNumber("33")
                .adverts(null)
                .build();
        var message1 = """
                Распечатаем все что пожелаете!\s
                Низкая стоимость, высокое качество, за короткие сроки.\s
                PLA. ABS. PETG.\s
                Звоните по номеру 8(983)-522-28-67, Алексей.""";
        var advertDto1 = new AdvertCreateDto("Услуги печати на 3D-принтере", message1, user.getId(), address1);

        var address2 = Address.builder()
                .id(null)
                .county("Московская область")
                .city("Москва")
                .district("Текстильщики")
                .street("Люблинская")
                .houseNumber("5к3")
                .adverts(null)
                .build();
        var message2 = """
                Предлагаем ваш широкий ассортимент пледов, матрасов, покрывал, одеял, подушек, мягких игрушек и \s
                иной мягкой и пушистой продукции нашей небольшой компании.\s
                Новым клиентам скидки!\s
                Звоните скорее! 8(800)-555-35-35.""";
        var advertDto2 = new AdvertCreateDto(
                "Лучшее постельное белье и мягкая мебель у нас!!!",
                message2,
                user.getId(),
                address2);

        return Arrays.asList(advertDto1, advertDto2);
    }

    @SneakyThrows
    private void loadImages() {
        List<Long> ids = userService.getByEmail(USER_EMAIL).getAdverts().stream()
                .map(Advert::getId)
                .sorted(Long::compare)
                .toList();

        File printerImageFile = new File(projectPath + "/advertapp-api/src/main/resources/static/printer.png");
        FileInputStream printerImageInput = new FileInputStream(printerImageFile);
        MultipartFile printerImage = new MockMultipartFile("printer.png", printerImageFile.getName(), "image/png", printerImageInput);

        File pillowImageFile = new File(projectPath + "/advertapp-api/src/main/resources/static/pillow.jpg");
        FileInputStream pillowImageInput = new FileInputStream(pillowImageFile);
        MultipartFile pillowImage = new MockMultipartFile("pillow.jpg", pillowImageFile.getName(), "image/jpg", pillowImageInput);

        imageService.uploadImage(printerImage, ids.get(0), null);
        imageService.uploadImage(pillowImage, ids.get(1), null);
    }
}

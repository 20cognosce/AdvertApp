package com.mirea.advertapp.runner;

import com.mirea.advertapp.domain.dto.AdvertCreateDto;
import com.mirea.advertapp.domain.entity.Address;
import com.mirea.advertapp.domain.entity.User;
import com.mirea.advertapp.domain.entityenum.Role;
import com.mirea.advertapp.domain.entityenum.UserAccountStatus;
import com.mirea.advertapp.service.AdvertService;
import com.mirea.advertapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserService userService;
    private final AdvertService advertService;

    private static final String ADMIN_EMAIL = "admin";
    private static final String USER_EMAIL = "user@mail.ru";

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
    }

    private List<AdvertCreateDto> getAdverts() {
        User user = userService.getByEmail(USER_EMAIL);

        var address1 = new Address(null, "Московская область", "Москва", "Дорогомилово", "Студенческая", "33", null);
        var message1 = """
                Распечатаем все что пожелаете!\s
                Низкая стоимость, высокое качество, за короткие сроки.\s
                PLA. ABS. PETG.\s
                Звоните по номеру 8(983)-522-28-67, Алексей""";
        var advertDto1 = new AdvertCreateDto("Услуги печати на 3D-принтере", message1, user.getId(), address1);

        var address2 = new Address(null, "Московская область", "Москва", "Текстильщики", "Люблинская", "5к3", null);
        var message2 = """
                Предлагаем ваш широкий ассортимент пледов, матрасов, покрывал, одеял, подушек, мягких игрушек и \s
                иной мягкой и пушистой продукции нашей небольшой компании.\s
                Новым клиентам скидки!\s
                Звоните скорее! 8(800)-555-35-35""";
        var advertDto2 = new AdvertCreateDto("Лучшее постельное белье и мягкая мебель у нас!!", message2, user.getId(), address2);

        return Arrays.asList(advertDto1, advertDto2);
    }

    private List<User> getUsers() {
        return Arrays.asList(
                new User(null, "Admin", "", null, ADMIN_EMAIL, userService.encodePassword("password".toCharArray()), Role.ADMIN, UserAccountStatus.ACTIVE, null),
                new User(null, "User", "", null, USER_EMAIL, userService.encodePassword("password".toCharArray()), Role.USER, UserAccountStatus.ACTIVE, null)
        );
    }
}

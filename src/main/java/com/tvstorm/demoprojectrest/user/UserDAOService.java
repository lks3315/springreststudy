package com.tvstorm.demoprojectrest.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDAOService {
    private static List<User> usersList = new ArrayList<>();

    private static int userCount = 4; // 현재 유저 수

    static {
        usersList.add(new User(1, "TV", new Date(),"pass1", "901010-1111111"));
        usersList.add(new User(2, "Storm", new Date(), "pass2", "801010-1111111"));
        usersList.add(new User(3, "Head", new Date(), "pass3", "701010-1111111"));
        usersList.add(new User(4, "End", new Date(), "pass4", "601010-1111111"));
    }

    public List<User> findAll() {
        return usersList;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }

        usersList.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user : usersList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = usersList.iterator(); // 배열 패키지를 순차적으로 가져와줌

        while (iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() != null) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}

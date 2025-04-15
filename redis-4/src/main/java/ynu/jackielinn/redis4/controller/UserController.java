package ynu.jackielinn.redis4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import ynu.jackielinn.redis4.entity.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String USER_KEY_PREFIX = "user:";

    @Autowired
    public UserController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        redisTemplate.opsForValue().set(USER_KEY_PREFIX + user.getId(), user);
        return "User created: " + user.getId();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) {
        return (User) redisTemplate.opsForValue().get(USER_KEY_PREFIX + id);
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable String id, @RequestBody User user) {
        if (redisTemplate.hasKey(USER_KEY_PREFIX + id)) {
            redisTemplate.opsForValue().set(USER_KEY_PREFIX + id, user);
            return "User updated: " + id;
        }
        return "User not found: " + id;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id) {
        redisTemplate.delete(USER_KEY_PREFIX + id);
        return "User deleted: " + id;
    }
}

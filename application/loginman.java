package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.User;

public class loginman {
  private static ArrayList<User> dk = new ArrayList<>(List.of(new User(1, "user1", "1234"),
      new User(2, "user2", "1234"), new User(3, "user3", "1234"), new User(4, "user4", "1234")));

  private int genNewId() {
    int lastIndex = dk.size() - 1;
    if (lastIndex < 0)
      return 1;
    return dk.get(lastIndex).getId() + 1;
  }

  public List<User> getAll() {
    return Collections.unmodifiableList(dk);
  }

  public User find(int id) {
    for (User user : dk) {
      if (user.getId() == id)
        return user;
    }
    return null;
  }

  public User find(String username, String password) {
    for (User user : dk) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password))
        return user;
    }
    return null;
  }

  public int create(User user) {
    int id = genNewId();
    User internal = new User(id, user.getUsername(), user.getPassword());
    dk.add(internal);
    return id;
  }

  public void update(User user) {
    User internal = find(user.getId());
    if (internal == null) {
      create(user);
      return;
    }

    internal.setUsername(user.getUsername());
    internal.setPassword(user.getPassword());
  }

  public void remove(int id) {
    User user = find(id);
    if (user != null) {
      dk.remove(user);
    }
  }
}

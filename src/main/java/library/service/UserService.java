package library.service;

import library.model.dto.BookDTO;
import library.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    public UserDTO findUser(UserDTO userDTO);

    public UserDTO findUserById(UserDTO userDTO);

    public Long createUser(UserDTO userDTO);

    public boolean updateUser(UserDTO userDTO);

    public List<BookDTO> getBooks(UserDTO userDTO);

    public List<UserDTO> getUsers();

}

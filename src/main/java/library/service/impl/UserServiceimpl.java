package library.service.impl;

import library.dao.UserDAO;
import library.helper.PropertiesReader;
import library.model.dto.BookDTO;
import library.model.dto.UserDTO;
import library.model.entity.User;
import library.service.UserService;
import library.helper.Transformer;

import java.util.LinkedList;
import java.util.List;

public class UserServiceimpl implements UserService {
    private static UserService service;
    private static UserDAO dao = PropertiesReader.getUserDao();
    private UserServiceimpl(){};

    public static synchronized UserService getInstance(){
            if (service == null)
                service = new UserServiceimpl();
            return service;
    }

    @Override
    public UserDTO findUser(UserDTO userDTO) {
        return Transformer.transformUser(dao.findUser(Transformer.transformUserDTO(userDTO)));
    }

    @Override
    public UserDTO findUserById(UserDTO userDTO) {
        return Transformer.transformUser(dao.findUserById(Transformer.transformUserDTO(userDTO)));
    }

    @Override
    public Long createUser(UserDTO userDTO) {
        return dao.createUser(Transformer.transformUserDTO(userDTO));
    }

    @Override
    public boolean updateUser(UserDTO user) {
        return dao.updateUser(Transformer.transformUserDTO(user));
    }


    @Override
    public List<BookDTO> getBooks(UserDTO userDTO) {
        List<BookDTO> booksDTO = new LinkedList<>();
        dao.getBooks(Transformer.transformUserDTO(userDTO)).forEach(book -> {
            booksDTO.add(Transformer.transformBook(book));
        });
        return booksDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> usersDTO = new LinkedList<>();
        dao.getUsers().forEach(user -> {
            usersDTO.add(Transformer.transformUser(user));
        });
        return usersDTO;
    }
}

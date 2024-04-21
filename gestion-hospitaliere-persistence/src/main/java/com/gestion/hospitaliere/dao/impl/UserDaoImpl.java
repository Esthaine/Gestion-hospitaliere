package com.gestion.hospitaliere.dao.impl;

import com.gestion.hospitaliere.dao.JpaRepositoryImpl;
import com.gestion.hospitaliere.dao.UserDao;
import com.gestion.hospitaliere.entity.Privilege;
import com.gestion.hospitaliere.entity.RolePrivilege;
import com.gestion.hospitaliere.entity.User;
import com.gestion.hospitaliere.model.PrivilegeDto;
import com.gestion.hospitaliere.model.RoleDto;
import com.gestion.hospitaliere.model.UserDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
public class UserDaoImpl extends JpaRepositoryImpl<User> implements UserDao{

    public UserDaoImpl(Class<User> clazz) {
        super(clazz);
    }

    @Override
    public UserDto register() {
        return null;
    }

    @Override
    public UserDto authentication() {
        return null;
    }

    @Override
    public UserDto findUserByName(String username) {

        UserDto userDto = null;
        List<RoleDto> roleDto = new ArrayList<>();
        List<PrivilegeDto> privilegeDto = new ArrayList<>();

        try (EntityManagerFactory em =
                     jakarta.persistence.Persistence.createEntityManagerFactory("gestion-hospitaliere-unit")) {
            EntityManager entityManager = em.createEntityManager();
            entityManager.getTransaction().begin();
            Query findUserByName = entityManager.createQuery("From User u where u.username =:username");
            findUserByName.setParameter("username", username);

            var resultList = findUserByName.getResultList();
            User user = null;

            if (!resultList.isEmpty()) {
                user = (User) resultList.get(0);
            }

            entityManager.getTransaction().commit();
            entityManager.close();

            if (user == null) {
                return userDto;
            }

            userDto = new UserDto();
            userDto.setUserId(user.getId());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setMotDePasse(user.getMotDePasse());

            if (user.getRole() != null) {
                RoleDto newRole = new RoleDto();
                newRole.setRoleId(user.getRole().getId());
                newRole.setRoleName(user.getRole().getName());
                newRole.setRoleDescription(user.getRole().getDescription());

                for (RolePrivilege privilege : user.getRole().getPrivileges()) {
                    PrivilegeDto newPrivilege = new PrivilegeDto();
                    newPrivilege.setPrivilegeId(privilege.getPrivilege().getId());
                    newPrivilege.setPrivilegeName(privilege.getPrivilege().getName());
                    newPrivilege.setPrivilegeDescription(privilege.getPrivilege().getDescription());
                    privilegeDto.add(newPrivilege);
                }

                newRole.setPrivilegeDto(privilegeDto);
                roleDto.add(newRole);
                userDto.setRoleDto(roleDto);
            }
        }
        return userDto;
    }
}

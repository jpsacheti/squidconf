package br.fema.edu.squidconf.model;

import java.util.Objects;

public class AuthUser {
    private Integer codigo;
    private String username;
    private String password;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthUser)) return false;
        AuthUser authUser = (AuthUser) o;
        return Objects.equals(codigo, authUser.codigo) &&
                Objects.equals(username, authUser.username) &&
                Objects.equals(password, authUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, username, password);
    }
}

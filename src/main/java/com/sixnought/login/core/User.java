package com.sixnought.login.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.security.Principal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Optional;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
      @NamedQuery(name = "User.findById",
                  query = "SELECT u FROM User u WHERE u.id = :id"),
      @NamedQuery(name = "User.findByUsernameAndPassword",
                  query = "SELECT u FROM User u WHERE u.username = :username "
                  + "and u.password = :password"),
      @NamedQuery(name = "User.findByUsername",
                  query = "SELECT u FROM User u WHERE u.username = :username"),
      @NamedQuery(name = "User.findByEmail",
                  query = "Select u FROM User u Where u.email = :email")})
                  
 public class User implements Principal, Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

   @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;

    @Basic(optional = true)
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;

    
   // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
   //  private final Set<Concubine> concubines = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Objects.requireNonNull(id);
        this.id = id;
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

    public String getEmail() {
       return email;
    }

    public void setEmail(String email) {
       this.email = email;
    }

    // public void addConcubine(final Concubine c) {
    //     Objects.requireNonNull(c);
    //     concubines.setUser(this);
    //     concubines.add(c);
    // }

    // public Set<Concubine> getConcubines() {
    //     return bookmarks;
    // }

    @Override
    public int hashCode() {
        return Objects.hash(this.id,
                this.username,
                this.password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password)
                && Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username
                + ", password=" + password
                // + ", concubines=" + 
                + '}';
    }

    @Override
    public String getName() {
        return username;
    }

    

}

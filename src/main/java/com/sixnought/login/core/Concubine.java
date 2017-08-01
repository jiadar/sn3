// package com.sixnought.login.core;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import java.io.Serializable;
// import java.util.Objects;
// import javax.persistence.Basic;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.ManyToOne;
// import javax.persistence.NamedQueries;
// import javax.persistence.NamedQuery;
// import javax.persistence.Table;
// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;
// import javax.xml.bind.annotation.XmlRootElement;

// @Entity
// @Table(name = "concubines")
// @XmlRootElement
// @NamedQueries({
//       @NamedQuery(name = "Concubine.findAll",
//                   query = "SELECT b FROM Concubine b"),
//          @NamedQuery(name = "Concubine.findById",
//                      query = "SELECT b FROM Concubine b WHERE b.id = :id"),
//          @NamedQuery(name = "Concubine.findByEthnicity",
//                      query = "SELECT b FROM Concubine b WHERE b.race = :race"),
//          @NamedQuery(name = "Concubine.findByDescription",
//                      query = "SELECT b FROM Concubine b "
//                      + "WHERE b.description = :description"),
//          @NamedQuery(name = "Concubine.findByUserId",
//                      query = "SELECT b FROM Concubine b WHERE b.user.id = :id"),
//          @NamedQuery(name = "Concubine.remove", query = "DELETE FROM Concubine b "
//                      + "where b.id = :id"),
//          @NamedQuery(name = "Concubine.findByIdAndUserId",
//                      query = "SELECT b FROM Concubine b WHERE b.id = :id AND "
//                      + "b.user.id = :userId")})

// public class Concubine implements Serializable {

//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Basic(optional = false)
//    @Column(name = "id")
//    private Integer id;

//    @Basic(optional = false)
//    @NotNull
//    @Size(min = 1, max = 255)
//    @Column(name = "race")
//    private String race;

//    @Size(max = 2048)
//    @Column(name = "description")
//    private String description;

//    @Basic(optional = false)
//    @JsonIgnore
//    @ManyToOne
//    private User user;

//    public Concubine() {
//    }

//    public Concubine(String race, String description) {
//       this.race = race;
//       this.description = description;
//    }

//    public Integer getId() {
//       return id;
//    }

//    public void setId(Integer id) {
//       this.id = id;
//    }

//    public String getRace() {
//       return race;
//    }

//    public void setRace(String race) {
//       this.race = race;
//    }

//    public String getDescription() {
//       return description;
//    }

//    public void setDescription(String description) {
//       this.description = description;
//    }

//    public User getUser() {
//       return user;
//    }

//    public void setUser(User user_id) {
//       this.user = user;
//    }

//    @Override
//    public int hashCode() {
//       return Objects.hash(this.id,
//                           this.race,
//                           this.description,
//                           this.user);
//    }

//    @Override
//    public boolean equals(Object obj) {
//       if (this == obj) {
//          return true;
//       }
//       if (obj == null) {
//          return false;
//       }
//       if (getClass() != obj.getClass()) {
//          return false;
//       }
//       final Concubine other = (Concubine) obj;
//       return Objects.equals(this.user, other.user)
//          && Objects.equals(this.race, other.race)
//          && Objects.equals(this.description, other.description)
//          && Objects.equals(this.id, other.id);
//    }

//    @Override
//    public String toString() {
//       return "Concubine{" + "id=" + id + ", race=" + race
//          + ", description=" + description
//          + ", user=" + Objects.toString(user) + '}';
//    }
   

// }

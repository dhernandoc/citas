package org.move2dbit.citas.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Profesional implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String nombre;

   @OneToMany(mappedBy = "idProfesional", cascade = CascadeType.REMOVE, orphanRemoval = true)
   private Set<Cita> idCita = new HashSet<Cita>();

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Profesional))
      {
         return false;
      }
      Profesional other = (Profesional) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public String getNombre()
   {
      return nombre;
   }

   public void setNombre(String nombre)
   {
      this.nombre = nombre;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      result += ", version: " + version;
      if (nombre != null && !nombre.trim().isEmpty())
         result += ", nombre: " + nombre;
      return result;
   }

   public Set<Cita> getIdCita()
   {
      return this.idCita;
   }

   public void setIdCita(final Set<Cita> idCita)
   {
      this.idCita = idCita;
   }

}
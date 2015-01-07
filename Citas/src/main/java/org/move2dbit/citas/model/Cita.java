package org.move2dbit.citas.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Cita implements Serializable
{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   private String fecha;

   @Column(length = 5)
   private String horaInicio;

   @Column(length = 5)
   private String horaFin;

   @ManyToOne
   private Profesional idProfesional;

   @ManyToOne
   private Solicitante idSolicitante;

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
      if (!(obj instanceof Cita))
      {
         return false;
      }
      Cita other = (Cita) obj;
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

   public String getFecha()
   {
      return fecha;
   }

   public void setFecha(String fecha)
   {
      this.fecha = fecha;
   }

   public String getHoraInicio()
   {
      return horaInicio;
   }

   public void setHoraInicio(String horaInicio)
   {
      this.horaInicio = horaInicio;
   }

   public String getHoraFin()
   {
      return horaFin;
   }

   public void setHoraFin(String horaFin)
   {
      this.horaFin = horaFin;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      result += ", version: " + version;
      if (horaInicio != null && !horaInicio.trim().isEmpty())
         result += ", horaInicio: " + horaInicio;
      if (horaFin != null && !horaFin.trim().isEmpty())
         result += ", horaFin: " + horaFin;
      return result;
   }

   public Profesional getIdProfesional()
   {
      return this.idProfesional;
   }

   public void setIdProfesional(final Profesional idProfesional)
   {
      this.idProfesional = idProfesional;
   }

   public Solicitante getIdSolicitante()
   {
      return this.idSolicitante;
   }

   public void setIdSolicitante(final Solicitante idSolicitante)
   {
      this.idSolicitante = idSolicitante;
   }

}
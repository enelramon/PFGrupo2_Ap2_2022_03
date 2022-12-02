using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ApiBarberShop.Models;

public partial class Servicio
{
    [Key]
    public int ServicioId { get; set; }

    public string Nombre { get; set; } = null!;

    public string? Imagen { get; set; }

    public DateTime FechaCreacion { get; set; }

    public DateTime? FechaModificacion { get; set; }

    public int UsuarioCreacionId { get; set; }

    public int? UsuarioModificacionId { get; set; }

    public int Status { get; set; }

    //public virtual ICollection<Cita>? Cita { get; } = new List<Cita>();
}

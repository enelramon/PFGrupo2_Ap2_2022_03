using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ApiBarberShop.Models;

public partial class Cliente
{
    [Key]
    public int ClienteId { get; set; }

    public string Nombre { get; set; }

    public string Apellido { get; set; }

    public string? Celular { get; set; }

    public DateTime? FechaNacimiento { get; set; }

    public string? Imagen { get; set; }

    public DateTime FechaCreacion { get; set; }

    public DateTime? FechaModificacion { get; set; } = null;

    public int Status { get; set; }

    //public virtual ICollection<Cita>? Cita { get; } = new List<Cita>();
}

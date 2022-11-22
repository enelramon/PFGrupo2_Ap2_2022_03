using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ApiBarberShop.Models;

public partial class Cliente
{
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    [Key]
    public int ClienteId { get; set; }

    public string Nombre { get; set; } = null!;

    public string Apellido { get; set; } = null!;

    public string? Celular { get; set; }

    public DateTime? FechaNacimiento { get; set; }

    public string? Imagen { get; set; }

    public DateTime FechaCreacion { get; set; }

    public DateTime? FechaModificacion { get; set; }

    public int Status { get; set; }

    public virtual ICollection<Cita>? Cita { get; } = new List<Cita>();
}

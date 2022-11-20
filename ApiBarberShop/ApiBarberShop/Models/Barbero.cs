using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ApiBarberShop.Models;

public partial class Barbero
{
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    [Key]
    public int BarberoId { get; set; }

    public DateTime FechaCreacion { get; set; }

    public DateTime? FechaModificacion { get; set; }

    public int Status { get; set; }

    public virtual ICollection<Cita> Cita { get; } = new List<Cita>();
}

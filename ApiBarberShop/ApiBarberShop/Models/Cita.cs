using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ApiBarberShop.Models;

public partial class Cita
{
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    [Key]
    public int CitaId { get; set; }

    public int ServicioId { get; set; }

    public int BarberoId { get; set; }

    public int ClienteId { get; set; }

    public DateTime Fecha { get; set; }

    public string? Mensaje { get; set; }

    public DateTime FechaCreacion { get; set; }

    public DateTime? FechaModificacion { get; set; }

    public int UsuarioCreacionId { get; set; }

    public int? UsuarioModificacionId { get; set; }

    public int Status { get; set; }

    [ForeignKey(nameof(BarberoId))]
    public virtual Barbero? Barbero { get; set; }

    [ForeignKey(nameof(ClienteId))]
    public virtual Cliente? Cliente { get; set; }

    [ForeignKey(nameof(ServicioId))]
    public virtual Servicio? Servicio { get; set; }
}

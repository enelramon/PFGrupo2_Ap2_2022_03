using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ApiBarberShop.DAL;
using ApiBarberShop.Models;
using Microsoft.Build.Framework;

namespace ApiBarberShop.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class CitasController : ControllerBase
    {
        private readonly Contexto _context;

        public CitasController(Contexto context)
        {
            _context = context;
        }

        [HttpGet("GetCitas")]
        public async Task<ActionResult<IEnumerable<Cita>>> GetCitas()
        {
            return await _context.Citas.ToListAsync();
        }

        [HttpGet("GetCita{id}")]
        public async Task<ActionResult<Cita>> GetCita(int id)
        {
            var cita = await _context.Citas.FindAsync(id);

            if (cita == null)
            {
                return NotFound();
            }

            return cita;
        }

        [HttpGet("GetClientesId{id}")]
        public IEnumerable<Cita> GetClienteId(int id)
        {
            var Clientes = _context.Citas.Where(c => c.Cliente.ClienteId == id && c.Status > 0).ToList();
            return Clientes;
        }

        [HttpPut("PutCitas{id}")]
        public async Task<IActionResult> PutCita(int id, Cita cita)
        {
            if (id != cita.CitaId)
            {
                return BadRequest();
            }

            _context.Entry(cita).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CitaExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }
        [HttpPost("PostCitas")]
        public async Task<ActionResult<Cita>> PostCita(Cita cita)
        {
            _context.Citas.Add(cita);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetCita", new { id = cita.CitaId }, cita);
        }

        [HttpDelete("Delete{id}")]
        public async Task<IActionResult> DeleteCita(int id)
        {
            var cita = await _context.Citas.FindAsync(id);
            if (cita == null)
            {
                return NotFound();
            }

            _context.Citas.Remove(cita);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool CitaExists(int id)
        {
            return _context.Citas.Any(e => e.CitaId == id);
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ApiBarberShop.DAL;
using ApiBarberShop.Models;

namespace ApiBarberShop.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class BarberosController : ControllerBase
    {
        private readonly Contexto _context;

        public BarberosController(Contexto context)
        {
            _context = context;
        }

        [HttpGet("GetBarberos")]
        public async Task<ActionResult<IEnumerable<Barbero>>> GetBarberos()
        {
            return await _context.Barberos.ToListAsync();
        }

        [HttpGet("GetBarberos{id}")]
        public async Task<ActionResult<Barbero>> GetBarbero(int id)
        {
            var barbero = await _context.Barberos.FindAsync(id);

            if (barbero == null)
            {
                return NotFound();
            }

            return barbero;
        }

        [HttpGet("GetAllBarberoStatus")]
        public IEnumerable<Barbero> GetAllBarberoStatus()
        {
            var barberos = _context.Barberos.Where(b =>b.Status > 0).ToList();
            return barberos;
        }

        [HttpPut("PutBarberos{id}")]
        public async Task<IActionResult> PutBarbero(int id, Barbero barbero)
        {
            if (id != barbero.BarberoId)
            {
                return BadRequest();
            }

            _context.Entry(barbero).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!BarberoExists(id))
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
        [HttpPost("PostBarberos")]
        public async Task<ActionResult<Barbero>> PostBarbero(Barbero barbero)
        {
            _context.Barberos.Add(barbero);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetBarbero", new { id = barbero.BarberoId }, barbero);
        }

        [HttpDelete("Delete{id}")]
        public async Task<IActionResult> DeleteBarbero(int id)
        {
            var barbero = await _context.Barberos.FindAsync(id);
            if (barbero == null)
            {
                return NotFound();
            }

            _context.Barberos.Remove(barbero);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool BarberoExists(int id)
        {
            return _context.Barberos.Any(e => e.BarberoId == id);
        }
    }
}

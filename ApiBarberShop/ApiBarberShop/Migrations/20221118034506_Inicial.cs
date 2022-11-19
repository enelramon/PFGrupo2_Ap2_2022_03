using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ApiBarberShop.Migrations
{
    /// <inheritdoc />
    public partial class Inicial : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Barberos",
                columns: table => new
                {
                    BarberoId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    FechaCreacion = table.Column<DateTime>(type: "datetime", nullable: false),
                    FechaModificacion = table.Column<DateTime>(type: "datetime", nullable: true),
                    Status = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Barberos", x => x.BarberoId);
                });

            migrationBuilder.CreateTable(
                name: "Clientes",
                columns: table => new
                {
                    ClienteId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Nombre = table.Column<string>(type: "nchar(30)", fixedLength: true, maxLength: 30, nullable: false),
                    Apellido = table.Column<string>(type: "nchar(30)", fixedLength: true, maxLength: 30, nullable: false),
                    Celular = table.Column<string>(type: "nchar(10)", fixedLength: true, maxLength: 10, nullable: true),
                    FechaNacimiento = table.Column<DateTime>(type: "date", nullable: true),
                    Imagen = table.Column<string>(type: "nchar(255)", fixedLength: true, maxLength: 255, nullable: true),
                    FechaCreacion = table.Column<DateTime>(type: "datetime", nullable: false),
                    FechaModificacion = table.Column<DateTime>(type: "datetime", nullable: true),
                    Status = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Clientes", x => x.ClienteId);
                });

            migrationBuilder.CreateTable(
                name: "Servicios",
                columns: table => new
                {
                    ServicioId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Nombre = table.Column<string>(type: "nchar(30)", fixedLength: true, maxLength: 30, nullable: false),
                    Imagen = table.Column<string>(type: "nchar(255)", fixedLength: true, maxLength: 255, nullable: true),
                    FechaCreacion = table.Column<DateTime>(type: "datetime", nullable: false),
                    FechaModificacion = table.Column<DateTime>(type: "datetime", nullable: true),
                    UsuarioCreacionId = table.Column<int>(type: "int", nullable: false),
                    UsuarioModificacionId = table.Column<int>(type: "int", nullable: true),
                    Status = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Servicios", x => x.ServicioId);
                });

            migrationBuilder.CreateTable(
                name: "Citas",
                columns: table => new
                {
                    CitaId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    ServicioId = table.Column<int>(type: "int", nullable: false),
                    BarberoId = table.Column<int>(type: "int", nullable: false),
                    ClienteId = table.Column<int>(type: "int", nullable: false),
                    Fecha = table.Column<DateTime>(type: "datetime", nullable: false),
                    Mensaje = table.Column<string>(type: "nchar(200)", fixedLength: true, maxLength: 200, nullable: true),
                    FechaCreacion = table.Column<DateTime>(type: "datetime", nullable: false),
                    FechaModificacion = table.Column<DateTime>(type: "datetime", nullable: true),
                    UsuarioCreacionId = table.Column<int>(type: "int", nullable: false),
                    UsuarioModificacionId = table.Column<int>(type: "int", nullable: true),
                    Status = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Citas", x => x.CitaId);
                    table.ForeignKey(
                        name: "FK_Citas_Barberos",
                        column: x => x.BarberoId,
                        principalTable: "Barberos",
                        principalColumn: "BarberoId");
                    table.ForeignKey(
                        name: "FK_Citas_Clientes",
                        column: x => x.ClienteId,
                        principalTable: "Clientes",
                        principalColumn: "ClienteId");
                    table.ForeignKey(
                        name: "FK_Citas_Servicios",
                        column: x => x.ServicioId,
                        principalTable: "Servicios",
                        principalColumn: "ServicioId");
                });

            migrationBuilder.CreateIndex(
                name: "IX_Citas_BarberoId",
                table: "Citas",
                column: "BarberoId");

            migrationBuilder.CreateIndex(
                name: "IX_Citas_ClienteId",
                table: "Citas",
                column: "ClienteId");

            migrationBuilder.CreateIndex(
                name: "IX_Citas_ServicioId",
                table: "Citas",
                column: "ServicioId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Citas");

            migrationBuilder.DropTable(
                name: "Barberos");

            migrationBuilder.DropTable(
                name: "Clientes");

            migrationBuilder.DropTable(
                name: "Servicios");
        }
    }
}

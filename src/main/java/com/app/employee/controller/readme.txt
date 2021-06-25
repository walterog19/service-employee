
package reserved for controllers that expose the project's REST API.

An example of a controller could be:

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.employee.dto.PlayerDTO;
import com.test.employee.exceptions.PlayerExistsConflictException;
import com.test.employee.exceptions.PlayerNotFoundException;
import com.test.employee.service.ITeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api(value = "/api", tags = "Operaciones con usuarios")
@Log4j2
public class RestApiController {

	private final ITeamService teamService;

	// -------------------------- Acceso a todos los usuarios --------------------
	// ///
	@ApiOperation(value = "Acceso a  todos los usuario.", notes = "Se pide el listado de todos los usuarios dados de alta en la BBDD.")
	@GetMapping(value = "/user", headers = "Accept=application/json")
	@ApiResponse(code = 401, message = "No se encontro el resultado")
	public ResponseEntity<List<PlayerDTO>> listAllUsers() {
		List<PlayerDTO> users = teamService.getPlayers();

		if (users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// User------------------------------------------
	@ApiOperation(value = "Busqueda de un usuario.", notes = "Busqueda de un usuario  dado de alta en memoria.")
	@ApiResponse(code = 401, message = "No se encontro el resultado")
	@GetMapping(value = "/user/{id}", headers = "Accept=application/json")
	public ResponseEntity<PlayerDTO> getUser(@PathVariable("id") long id) throws PlayerNotFoundException {

		if (log.isInfoEnabled()) {
			log.info("Buscando usuario con id {}", id);
		}
		PlayerDTO player = teamService.getPlayerById(id);
		if (player == null) {

			if (log.isWarnEnabled()) {
				log.error("Usuario con id {} no encontrado.", id);
			}
			throw new PlayerNotFoundException("User with id " + id + " not found");
		}
		return new ResponseEntity<>(player, HttpStatus.OK);
	}

	// ----------------- Creacion de Usuario -------------------//
	@ApiOperation(value = "Alta de un usuario a partir de un id.", notes = "Insercion de un usuario en memoria.")
	@ApiResponse(code = 401, message = "No se encontro el resultado")
	@PostMapping(value = "/user")
	public ResponseEntity<PlayerDTO> createUser(@RequestBody PlayerDTO player) throws PlayerExistsConflictException {

		if (log.isInfoEnabled()) {
			log.info("Creando usuario : {}", player);
		}
		teamService.insert(player);

		return new ResponseEntity<>(player, HttpStatus.CREATED);
	}

	/// --------------- Actualización de los usuarios-------------------//
	@ApiOperation(value = "Modificacion de un usuario a partir de una peticion Put.", notes = "Modificacion de un usuario en memoria.")
	@ApiResponse(code = 401, message = "No se encontro el resultado")
	@PutMapping(value = "/user/{id}")
	public ResponseEntity<PlayerDTO> updateUser(@PathVariable("id") long id, @RequestBody PlayerDTO player) throws PlayerNotFoundException {

		if (log.isInfoEnabled()) {
			log.info("Usuario actualizado con id {}", id);
		}

		PlayerDTO currentPlayer = teamService.getPlayerById(id);

		if (currentPlayer == null) {
			if (log.isWarnEnabled()) {
				log.error("Imposible actualizar. Usuario con id {} no se encontro.", id);
			}

			throw new PlayerNotFoundException("Imposible actualizar. Usuario con id " + id + " no se encontro.");
		}
		
		currentPlayer.setName(player.getName());
		currentPlayer.setAge(player.getAge());
		currentPlayer.setNumber(player.getNumber());

		teamService.update(currentPlayer);

		return new ResponseEntity<>(currentPlayer, HttpStatus.OK);
	}

	// ------------------- Eliminacion de
	// usuario-----------------------------------------
	@ApiOperation(value = "Eliminacion de un usuario a partir de una peticion Put.", notes = "Eliminacion de un usuario en memoria")
	@ApiResponse(code = 401, message = "No autorizado")
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<PlayerDTO> deleteUser(@PathVariable("id") long id) throws PlayerNotFoundException {

		if (log.isInfoEnabled()) {
			log.info("Buscando & eliminando usuario con id {}", id);
		}
		PlayerDTO player = teamService.getPlayerById(id);
		
		if (player == null) {
			if (log.isWarnEnabled()) {
				log.error("No es posible eliminarlo. Usuario con id {} no encontrado.", id);
			}

			throw new PlayerNotFoundException(
					"No es posible eliminarlo. Usuario con id " + id + " no encontrado.");
		}
		teamService.delete(id);
		return new ResponseEntity<>(player, HttpStatus.ACCEPTED);
	}

}

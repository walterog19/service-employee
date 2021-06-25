package for classes (interfaces) of type Mapper (mapstruct) that allow the conversion of
entity objects <-> disc. They are useful for the passage of objects between layers.

import java.util.List;

import org.mapstruct.Mapper;

import com.test.employee.entity.Player;
import com.test.employee.dto.PlayerDTO;


@Mapper
public interface PlayerMapper {

	PlayerDTO playerToPlayerDto(Player player);

	List<PlayerDTO> playerToPlayerDto(List<Player> player);

	Player playerDtoToPlayer(PlayerDTO player);

	List<Player> playerDtoToPlayer(List<PlayerDTO> player);
}

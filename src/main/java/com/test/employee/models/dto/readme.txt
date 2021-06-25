
DTO-type object package for data transfer between layers and communication with the outside (API).

An example DTO class could be:

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2004440174929362136L;

	private Long idPlayer;

	private String name;

	private Integer age;

	private Long number;

	@Override
	public String toString() {
		return number + " - " + name + " (" + age + ")";
	}

}

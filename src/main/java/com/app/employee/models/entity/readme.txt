
Package for entity classes that map the DB model to java classes. These classes never
they must exit the services layer to the outside.

An example entity class could be:


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PLAYER")
public class Player {

	@Id
	@Column(name = "ID_PLAYER", nullable = false)
	private Long idPlayer;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "AGE", nullable = false)
	private Integer age;

	@Column(name = "NUMBER", nullable = false)
	private Long number;

	@Override
	public String toString() {
		return number + " - " + name + " (" + age + ")";
	}

}
package co.edu.uniquindio.sputify.model;

import java.time.LocalTime;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Song implements Comparable<Song>{

	@Include
	private String name;
	private String albumName;
	private String cover;
	private Integer year;
	private LocalTime duration;
	private String url;
	private Genre genre;

	@Getter
	public enum Genre {
		ROCK("Rock"), POP("Pop"), PUNK("Punk"), REGGAETON("Reggaeton"), ELECTRONICA("Electronica");

		private String value;

		Genre(String value) {
			this.value = value;
		}

		public static Genre genreOf(String value) {
			Genre[] values = values();
			for (Genre genre : values) {
				if (genre.getValue().equals(value))
					return genre;
			}
			return null;
		}

	}

	@Override
	public int compareTo(Song o) {
		return this.name.compareTo(o.name);
	}

}

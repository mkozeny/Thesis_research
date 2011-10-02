@Entity
public class Role {
	@Length(max = 50)
	String name;

	@Length(max = 150)
	String description;

	@Test
	public void showMeTheFunny() {
		System.out.println("Here you have funny");
	}

	public void foo() {
		System.out.println("Foo");
	}

	public void bar() {
		System.out.println("Bar");
	}
}
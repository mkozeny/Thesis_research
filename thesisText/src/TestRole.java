public class TestRole {
	public void test(Role role) throws Exception {
		for (Field f : Role.class.getFields()) {
			Annotation[] annotations = f.getDeclaredAnnotations();
			for (Annotation a : annotations) {
				if (a.annotationType().equals(Length.class)) {
					if (f.get(role) instanceof String && ((String)f.get(role)).length() > ((Length) a).max())
						throw new Exception(
								"Unacceptable length of field name of class Role");
				}
			}
		}
	}
}

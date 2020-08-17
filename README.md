# DTOConverter

This Library contains a IDTOConverter.java interface which consists of several default methods.
While implementing the interface, you will be able to convert POJO classes to desired DTO classes.
You will need to implement an apply() methods which defines how to perform the conversion between the two classes.
By calling the convert() methods you will have following conversion options:
1. T -> D
2. List (T) -> List <D>
3. Iterablet <T> -> List <D>
4. Page <T> -> Page <D> (org.springframework.data.domain.Page)

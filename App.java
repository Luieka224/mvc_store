class App{
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        var view = new View();
        var model = new Model();
        var controller = new Controller(view, model);
    }
}
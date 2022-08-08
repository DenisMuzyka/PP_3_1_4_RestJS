const findCurrentUser = "http://localhost:8080/api/userinfo";
let currentUser;

// Функция для обновления перменной
function getCurrentUser() {
    currentUser = fetch(findCurrentUser).then(response => response.json());
}

// Обновляем переменную
getCurrentUser();

// Функция конструирует шапку сайта
function currentUserHeader() {
    currentUser.then(user => {
        $("<strong>" + user.email + "</strong>&nbsp;<span>with roles:" + user.roles.map(r => {
            if (r.role === "ROLE_ADMIN") {
                return " Admin"
            } else if (r.role === "ROLE_USER") {
                return " User"
            }
        }) + "</span>").appendTo("#currentUserHeader")
    });
}

// Функция конструирует таблицу с информацией о текущем юзере
function currentUserInfo() {
    currentUser.then(user => {
        $("#usersInfoTable").children().remove();
        $(
            "<tr>" +
            "<td class=\"text-center\">" + user.id + "</td>" +
            "<td class=\"text-center\">" + user.firstName + "</td>" +
            "<td class=\"text-center\">" + user.lastName + "</td>" +
            "<td class=\"text-center\">" + user.age + "</td>" +
            "<td class=\"text-center\">" + user.email + "</td>" +
            "</tr>")
            .appendTo("#usersInfoTable")
    });
}
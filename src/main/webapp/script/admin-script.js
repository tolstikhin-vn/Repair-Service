const userSelect = document.getElementById("user-select");
const searchInput = document.getElementById("search-input");
const submitButton = document.querySelector("button[type=submit]");

submitButton.addEventListener("click", (event) => {
    event.preventDefault();
    const searchTerm = searchInput.value;
    const users = userSelect.options;
    let foundUser = null;
    for (let i = 0; i < users.length; i++) {
        const user = users[i];
        if (user.textContent.includes(searchTerm)) {
            foundUser = user;
            break;
        }
    }
    if (foundUser) {
        userSelect.value = foundUser.value;
        getUserData(foundUser.value);
    } else {
        userSelect.value = "";
        getUserData("");
    }
});

function getUserData(userId) {
    const tableCells = document.querySelectorAll('.user-table td');
    tableCells.forEach((cell) => {
        cell.textContent = '';
    });

    if (!userId) {
        return;
    }

    fetch("user-data?id=" + userId)
        .then((response) => response.json())
        .then((data) => {
            if (data) {
                const {
                    id,
                    login,
                    name,
                    surname,
                    active,
                    deleted,
                    passwordFailCount,
                    defaultPasswordFailCount,
                    lastLogin,
                    lastLogout
                } = data;
                const userIdCell = document.querySelector('.user-id');
                const userLoginCell = document.querySelector('.user-login');
                const userNameCell = document.querySelector('.user-name');
                const userSurnameCell = document.querySelector('.user-surname');
                const userActiveCell = document.querySelector('.user-active');
                const userDeletedCell = document.querySelector('.user-deleted');
                const userPasswordFailCountCell = document.querySelector('.user-pass-fail-count');
                const userDefPasswordFailCountCell = document.querySelector('.user-def-pass-fail-count');
                const userLastLoginCell = document.querySelector('.user-last-login');
                const userLastLogoutCell = document.querySelector('.user-last-logout');

                const elements = document.querySelectorAll('[name="id-user-for-changing"]');
                for (let i = 0; i < elements.length; i++) {
                    elements[i].value = id;
                }
                // document.querySelector('[name="id-user-for-changing"]').value = id

                userIdCell.textContent = id;
                userLoginCell.textContent = login;
                userNameCell.textContent = name;
                userSurnameCell.textContent = surname;
                userActiveCell.textContent = active ? 'Да' : 'Нет';
                userDeletedCell.textContent = deleted ? 'Да' : 'Нет';
                userPasswordFailCountCell.textContent = passwordFailCount;
                userDefPasswordFailCountCell.textContent = defaultPasswordFailCount;
                userLastLoginCell.textContent = lastLogin ? new Date(lastLogin).toLocaleString() : '';
                userLastLogoutCell.textContent = lastLogout ? new Date(lastLogout).toLocaleString() : '';
            }
        })
        .catch((error) => {
            console.error(error);
        });
}
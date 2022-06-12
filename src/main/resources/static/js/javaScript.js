const ADMIN = 'http://localhost:8080/adminRest'

let ROLES = [
    {
        "id": 1,
        "name": "ADMIN",
        "users": null,
        "authority": "ADMIN"
    },
    {
        "id": 2,
        "name": "USER",
        "users": null,
        "authority": "USER"
    }
]
function addRoles (roleList) {
    let array = []
    let options = document.querySelector(roleList).options
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            array.push(ROLES[i])
        }
    }
    return array
}

function User(x) {
    if (x !== 'C') {
        this.id = $(`#id${x}`).val()
    }
    this.username = $(`#username${x}`).val()
    this.password = $(`#password${x}`).val()
    this.first_name = $(`#first_name${x}`).val()
    this.last_name = $(`#last_name${x}`).val()
    this.age = $(`#age${x}`).val()
    this.email = $(`#email${x}`).val()
    this.roles = addRoles(`#roles${x}`)
}

getUser()
tableUsers()

function tableUsers() {
  fetch(ADMIN).then(
    res => {
        res.json().then(
            data => {
                if (data.length > 0) {
                    let temp = ''
                    console.log(data)
                    data.forEach(u => {
                        temp += '<tr>'
                        temp += '<td>' + u.id
                        temp += '<td>' + u.first_name
                        temp += '<td>' + u.last_name
                        temp += '<td>' + u.age
                        temp += '<td>' + u.email
                        let roles = ''
                        u.roles.forEach(x => roles += x.name + ' ')
                        temp += '<td>' + roles
                        temp += '<td>' + `<a type="button" class="btn btn-sm btn-primary"` +
                            `onclick="form(${u.id}, 'U', 'update')">Edit</a>`
                        temp += '<td>' + `<a type="button" class="btn btn-danger"` +
                            `onclick="form(${u.id}, 'D', 'delete')">Delete</a>`

                    })
                    document.getElementById('tableUsers').innerHTML = temp

                }
            }
        )
    }
  )
}

function getUser() {
    fetch(ADMIN + "/user").then(
        res => {
            res.json().then(
                data => {
                    console.log(data)
                    let temp = ''
                    temp += '<tr>'
                    temp += '<td>' + data.id
                    temp += '<td>' + data.first_name
                    temp += '<td>' + data.last_name
                    temp += '<td>' + data.age
                    temp += '<td>' + data.email
                    let roles = ''
                    data.roles.forEach(x => roles += x.name + ' ')
                    temp += '<td>' + roles

                    document.getElementById('aboutUser').innerHTML = temp

                    document.querySelector('#polEmail').textContent = data.email
                    let roleP = ''
                    data.roles.forEach(x => roleP += x.name + ' ')
                    document.querySelector('#polRoles').textContent = roleP
                }
            )
        }
    )
}

function createUser() {
    fetch(ADMIN,
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json; charset=utf-8'
            },
            method: "POST",
            body: JSON.stringify(new User('C'))
        }).then(() => {
        tableUsers()
    })
}

function form(id, x, action) {
    fetch(ADMIN + '/' + id).then(
        res => {
            res.json().then(
                data => {
                    console.log(action)
                    $(`#id${x}`).val(data.id)
                    $(`#username${x}`).val(data.username)
                    $(`#password${x}`).val('')
                    $(`#first_name${x}`).val(data.first_name)
                    $(`#last_name${x}`).val(data.last_name)
                    $(`#age${x}`).val(data.age)
                    $(`#email${x}`).val(data.email)
                    $(`#${action}`).attr('onclick', `${action}User(${data.id})`)
                    $(`#${action}User`).modal('show')
                }
            )
        }
    )
}

function updateUser(id) {
    fetch(ADMIN,
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json; charset=utf-8'
            },
            method: "PUT",
            body: JSON.stringify(new User('U'))
        }).then(() => {
        $('#updateUser').modal("hide")
        tableUsers()
    })
}

function deleteUser(id) {
    fetch(ADMIN + "/" + id,
        {
            method: "DELETE",
        }).then(() => {
        $('#deleteUser').modal("hide")
        tableUsers()
    })
}



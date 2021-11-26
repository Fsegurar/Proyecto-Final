let catBreeds = [];
var rowId = 0;

fetch('https://dog.ceo/api/breeds/list/all')
    .then(response => response.json())
    .then(data => {
        let petBreed = document.getElementById("dograza-input");
        Object.keys(data.message).map((breed) => {
            let option = document.createElement("option");
            option.innerHTML = breed;
            petBreed.appendChild(option);
        });
    });

fetch('https://api.thecatapi.com/v1/breeds')
    .then(response => response.json())
    .then(data => {
        catBreeds = data;
        let catBreed = document.getElementById("catraza-input");
        data.forEach((breed) => {
            let option = document.createElement("option");
            option.innerHTML = breed.name;
            catBreed.appendChild(option);
        });
    });

const case1 = document.getElementById("case1");
const case2 = document.getElementById("case2");

function uploaddata(){
    let user;
    const cookies = document.cookie.split(";");
    for(let i =0; i<cookies.length; i++) {
        if (cookies[i].includes("username")) {
           username= cookies[i].split("=")[1];
        }
    }
    let owner;
    let neighbor;
    fetch('http://localhost:8080/Proyecto-Final-1.0-SNAPSHOT/api/owners')
        .then(response => response.json())
        .then(data =>{
            console.log(data)
            data.forEach((id)=>{
                if(user===id.username){
                    owner = id.person_id;
                    neighbor=id.neighborhood;
                }
            });

        });
    fetch('http://localhost:8080/Proyecto-Final-1.0-SNAPSHOT/api/pets')
        .then(response => response.json())
        .then(data =>{
            console.log(data)
            data.forEach((pet)=>{
                if(owner===pet.owner_id){
                    let tr = document.createElement("tr")
                    tr.setAttribute("id", "row-" + (rowId+1));
                    let tdimg = document.createElement("td");
                    let img = document.createElement("img");
                    img.setAttribute("class","rounded")
                    img.setAttribute("id","img-"+(i+1))
                    img.setAttribute("alt","Cinque Terre")
                    img.setAttribute("width","200" )
                    img.setAttribute("height","156")
                    img.setAttribute("src",pet.picture)
                    tdimg.appendChild(img);
                    tr.appendChild(tdimg);
                    let pets= {

                        petname: pet.name,
                        microchipnum: pet.microchip,
                        petspecie: pet.species,
                        petsex: pet.sex,
                        petsize: pet.size,
                        neightborinput: neighbor

                    }
                    Object.keys(pets).forEach((key) => {
                        let td = document.createElement("td");
                        td.innerHTML= pets[key];
                        tr.appendChild(td);
                    });
                    let tdActions = document.createElement("td");

                    //tr.appendChild(tdActions);
                    let inputeditar = document.createElement("input");
                    inputeditar.setAttribute("id", "editar-" + (i+1));
                    inputeditar.setAttribute("type", "button");
                    inputeditar.setAttribute("data-toggle","modal" )
                    inputeditar.value = "Editar";

                    inputeditar.onclick = function() {
                        console.log("se leyo editar 1")
                        let id = this.getAttribute("id");
                        id = +id.replace("editar-", "");
                        if(pet.microchip==""){
                            document.getElementById("editar-"+id).setAttribute("data-target","case1")
                            case1.style.display = "block";
                            case1.style.paddingRight = "17px";
                            case1.className="modal fade show";
                            document.getElementById("lable-m").setAttribute("class",+id+"");
                        }else{
                            document.getElementById("editar-"+id).setAttribute("data-target","case2")
                            case2.style.display = "block";
                            case2.style.paddingRight = "17px";
                            case2.className="modal fade show";
                            document.getElementById("lable-m").setAttribute("class",+id+"");
                        }
                    };
                    tdActions.appendChild(inputeditar);
                    tr.appendChild(tdActions);
                    document.getElementById("body-table").appendChild(tr);
                }
            })
        })


};
uploaddata();


document.getElementById("save-edit1").onclick= function(){
    let id = document.getElementById("lable-m").getAttribute("class")
    document.getElementById("lable-m").removeAttribute("class")
    case1.style.display = "none";
    case1.className="modal fade";

};
document.getElementById("close-edit1").onclick=function() {
    case1.style.display = "none";
    case1.className="modal fade";
};

document.getElementById("save-edit2").onclick= function(){
    let id = document.getElementById("lable-m").getAttribute("class")
    document.getElementById("lable-m").removeAttribute("class")
    case2.style.display = "none";
    case2.className="modal fade";

};
document.getElementById("close-edit2").onclick=function() {
    case2.style.display = "none";
    case2.className="modal fade";
};



document.getElementById("petspecies-input").onclick = function(){
    let raza =document.getElementById("petspecies-input").value;
    if(raza=="Perro"){
        console.log("Perro")
        document.getElementById("dograza-input").removeAttribute("disabled")
        document.getElementById("catraza-input").setAttribute("disabled",true)
    }else if(raza=="Gato"){
        console.log("gato")
        document.getElementById("catraza-input").removeAttribute("disabled")
        document.getElementById("dograza-input").setAttribute("disabled",true)
    }
};

(function() {
    var forms = document.getElementsByClassName('needs-validation');
    var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener('submit', function(event) {
            if (form.checkValidity() === false) {
                console.log(form.checkValidity())
                event.preventDefault();
                event.stopPropagation();
            }else if(form.checkValidity()===true){
                rowId += 1;
                event.preventDefault();
                event.stopPropagation();
                alert("Registro creado");
                window.location.reload()
                console.log(document.getElementById("row-"+rowId))
            };
            form.classList.add('was-validated');
        }, false);
    });
    ;
})();






const addAntecedent = document.querySelector("#add-antecedant");
const addExamen = document.querySelector("#add-examen");
const addMedicament = document.querySelector("#add-medicament");


// container antecedants
const antecedantContainer = document.querySelector(".antecedants-items-form");


const questionContainer = document.querySelector(".questions");


// medicaments container

const medicamentContainer = document.querySelector('.medicament-item-container');




if (addAntecedent) {
    addAntecedent.addEventListener('click', ()=>{
        //alert("Antecedent");
        addAntecedentDynamicFields();
    });
}



addExamen.addEventListener('click', ()=>{
    addExamenLabo();
});


// addMedicament.addEventListener('click', ()=>{
//     addMedicamentAction()
// });




function addAntecedentDynamicFields(){

    const antecedentItemsForm = document.createElement('div');
    antecedentItemsForm.className = 'antecedants-item-form';

    const formTableCompletH = document.createElement('div');
    formTableCompletH.className = 'form-table-complet-h';

    const formTable1 = document.createElement('div');
    formTable1.className = 'form-table';

    const formTable2 = document.createElement('div');
    formTable2.className = 'form-table';

    const labelDateDebut = document.createElement('label');
    labelDateDebut.innerHTML = "Date debut:";

    const inputDateDebut = document.createElement('input');
    inputDateDebut.type = 'date';
    inputDateDebut.name = 'date_debuts';

    const labelDateFin = document.createElement('label');
    labelDateFin.innerHTML = "Date fin:"

    const inputDateFin = document.createElement('input');
    inputDateFin.type = 'date';
    inputDateFin.name = 'date_fins';


    formTable1.appendChild(labelDateDebut);
    formTable1.appendChild(inputDateDebut);

    formTable2.appendChild(labelDateFin);
    formTable2.appendChild(inputDateFin);

    formTableCompletH.appendChild(formTable1);
    formTableCompletH.appendChild(formTable2);


    const formTableCompletType = document.createElement('div');
    formTableCompletType.className = 'form-table-complet';

    const labelType = document.createElement('label');
    labelType.innerHTML = 'Type:';

    const inputType = document.createElement('input');
    inputType.type = 'text'
    inputType.name = 'type';

    formTableCompletType.appendChild(labelType);
    formTableCompletType.appendChild(inputType);


    const formTableCompletDescription = document.createElement('div');
    formTableCompletDescription.className = 'form-table-complet';

    const labelDescription = document.createElement('label');
    labelDescription.innerHTML = 'Description:'

    const textArea = document.createElement('textarea');
    textArea.name = 'descriptions';

    formTableCompletDescription.appendChild(labelDescription);
    formTableCompletDescription.appendChild(textArea);


    antecedentItemsForm.appendChild(formTableCompletH);
    antecedentItemsForm.appendChild(formTableCompletType);
    antecedentItemsForm.appendChild(formTableCompletDescription);


    const remove = document.createElement('a');
    remove.classList.add("action-button");
    remove.classList.add("action-minus");
    remove.innerHTML = "<i class='fa fa-minus' aria-hidden='true'></i>";


    //removeAntecedent
    remove.addEventListener('click', function() {
        console.log('remove')
        this.parentElement.remove();
    });

    antecedentItemsForm.appendChild(remove);

    antecedantContainer.appendChild(antecedentItemsForm);
}



function addExamenLabo(){

    const questionDiv = document.createElement('div');
    questionDiv.className = 'question';


    const questionItemDiv = document.createElement('div');
    questionItemDiv.className = 'questions-items';

    const titleDemandH3 = document.createElement('h3');
    titleDemandH3.innerHTML = 'Demande:';

    const labelDemand = document.createElement('label');
    labelDemand.innerHTML = 'Examen:';

    const question = document.createElement('input');
    question.type = 'text';
    question.name = 'questions';

    const labelAppreciation = document.createElement('label');
    labelAppreciation.innerHTML = 'Appreciation:';

    const appreciation = document.createElement('input');
    appreciation.type = 'text';
    appreciation.name = 'appreciation'


    questionItemDiv.appendChild(titleDemandH3);
    questionItemDiv.appendChild(labelDemand);
    questionItemDiv.appendChild(question);
    questionItemDiv.appendChild(labelAppreciation);
    questionItemDiv.appendChild(appreciation);


    const responseItemDiv = document.createElement('div');
    responseItemDiv.className = 'reponse-items';

    const titleReponseDemandH3 = document.createElement('h3');
    titleReponseDemandH3.innerHTML = 'Reponse:';

    const labelResponse = document.createElement('label');
    labelResponse.innerHTML = 'Resultat:';

    const reponse = document.createElement('input');
    reponse.type = 'text';
    reponse.name = 'reponses';
    reponse.disabled = true;

    const labelAppreciationReponse = document.createElement('label');
    labelAppreciationReponse.innerHTML = 'Appreciation Reponse:';

    const appreciationResponse = document.createElement('input');
    appreciationResponse.type = 'text';
    appreciationResponse.name = 'appreciation_reponse';
    appreciationResponse.disabled = true;

    responseItemDiv.appendChild(titleReponseDemandH3);
    responseItemDiv.appendChild(labelResponse);
    responseItemDiv.appendChild(reponse);
    responseItemDiv.appendChild(labelAppreciationReponse);
    responseItemDiv.appendChild(appreciationResponse);



    const itemQuest = document.querySelector(".question-item");

    const removeExamen = document.createElement('a');
    removeExamen.classList.add("action-button");
    removeExamen.classList.add("action-minus");
    removeExamen.innerHTML = "<i class='fa fa-minus' aria-hidden='true'></i>";


    removeExamen.addEventListener('click', function(){
        this.parentElement.remove();
    });

    questionDiv.appendChild(removeExamen);
    questionDiv.appendChild(questionItemDiv);
    questionDiv.appendChild(responseItemDiv);

    questionContainer.appendChild(questionDiv);

}



// function addMedicamentAction(){
//
//     const medicamentItem = document.createElement('div');
//     medicamentItem.className = 'medicament-item';
//
//     const labelMedicament = document.createElement("label");
//     labelMedicament.innerHTML = "Nom Medicament: "
//
//     const medicamentField = document.createElement("input");
//     medicamentField.type = "text";
//     medicamentField.name = "medicament";
//
//     medicamentItem.appendChild(labelMedicament);
//     medicamentItem.appendChild(medicamentField);
//
//
//
//     const remove = document.createElement('a');
//     remove.classList.add("action-button");
//     remove.classList.add("action-minus");
//     remove.innerHTML = "<i class='fa fa-minus' aria-hidden='true'></i>";
//
//
//     //removeAntecedent
//     remove.addEventListener('click', function() {
//         console.log('remove')
//         this.parentElement.remove();
//     });
//
//     medicamentItem.appendChild(remove);
//     medicamentContainer.appendChild(medicamentItem);
//
// }
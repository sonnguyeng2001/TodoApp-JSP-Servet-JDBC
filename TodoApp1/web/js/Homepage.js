/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const btnOpenModal = document.querySelector("#btn-openModal");
const btnCloseModal = document.querySelector("#btn-closeModal");
const modalWrapper = document.querySelector(".modal_wrapper");

btnOpenModal.addEventListener("click", () => {
	modalWrapper.classList.add("show");
});

btnCloseModal.addEventListener("click", () => {
	modalWrapper.classList.remove("show");
});
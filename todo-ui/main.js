const btnOpenModal = document.querySelector("#btn-openModal");
const btnCloseModal = document.querySelector("#btn-closeModal");
const modalWrapper = document.querySelector(".modal_wrapper");

btnOpenModal.addEventListener("click", () => {
	modalWrapper.classList.add("show");
});

btnCloseModal.addEventListener("click", () => {
	modalWrapper.classList.remove("show");
});
//Coleta o cliente que foi passado para essa página
const cliente = JSON.parse(localStorage.getItem("clienteSelecionado"));

//Coleta os dados necessários e faz as atribuições necessárias
document.getElementById("nome-cliente").textContent = cliente.nome;
document.getElementById("cpf-cliente").textContent = cliente.cpf;
document.getElementById("data-nascimento-cliente").textContent =
    cliente.data_nascimento;
document.getElementById("endereco-cliente").textContent = cliente.endereco;
const modalEditar = document.getElementById("modal-editar");
const modalCriarContato = document.getElementById("modal-criar-contato");
const tipoValorContato = document.getElementById("tipo");
const tipoValorContatoEditar = document.getElementById("tipo-editar");
const modalEditarContato = document.getElementById("modal-editar-contato");

//Atribui um campo inicial à modal de criação de contato, ja que tem 2 tipos dinâmicos
document.getElementById("valor-container").innerHTML = `
            <label for="valor">Telefone</label>
            <input minlength="10" maxlength="11" id="valor" placeholder="Digite seu telefone com DDD" type="text" required>
        `;

//Atribui dinamicamente qual tipo de input sera inserido na modal baseado no tipo do valor do contato
tipoValorContato.addEventListener("change", () => {
    document.getElementById("valor-container").innerHTML = "";
    const valorElement = document.createElement("div");
    valorElement.className = "valor-container-div";
    if (tipoValorContato.value === "Telefone") {
        valorElement.innerHTML = `
            <label for="valor">Telefone</label>
            <input minlength="10" maxlength="11" id="valor" placeholder="Digite seu telefone com DDD" type="text" required>
        `;
    } else if (tipoValorContato.value === "E-mail") {
        valorElement.innerHTML = `
            <label for="valor">E-mail</label>
            <input id="valor" placeholder="Digite seu email" type="email" required>
        `;
    }
    document.getElementById("valor-container").appendChild(valorElement);
});

const telefoneContato = document.getElementById("valor");
telefoneContato.addEventListener("input", (evento) => {
    evento.target.value = evento.target.value.replace(/\D/g, "");
});

//Atribui um campo inicial à modal de edição de contato, ja que tem 2 tipos dinâmicos
document.getElementById("valor-container-editar").innerHTML = `
            <label for="valor">Telefone</label>
            <input minlength="10" maxlength="11" id="valor-editar" placeholder="Digite seu telefone com DDD" type="text" required>
        `;

//Atribui dinamicamente qual tipo de input sera inserido na modal baseado no tipo do valor do contato, mas para a modal de edição
tipoValorContatoEditar.addEventListener("change", () => {
    document.getElementById("valor-container-editar").innerHTML = "";
    const valorElement = document.createElement("div");
    valorElement.className = "valor-container-div-editar";
    if (tipoValorContatoEditar.value === "Telefone") {
        valorElement.innerHTML = `
            <label for="valor">Telefone</label>
            <input minlength="10" maxlength="11" id="valor-editar" placeholder="Digite seu telefone com DDD" type="text" required>
        `;
    } else if (tipoValorContatoEditar.value === "E-mail") {
        valorElement.innerHTML = `
            <label for="valor">E-mail</label>
            <input id="valor-editar" placeholder="Digite seu email" type="email" required>
        `;
    }
    document.getElementById("valor-container-editar").appendChild(valorElement);
});

//Função assincrona que busca os contatos do cliente especifico
async function buscarContatosCliente() {
    document.getElementById("lista-contatos").innerHTML = "";
    try {
        const response = await fetch(
            `http://localhost:8080/clientes/${cliente.id}/contatos/listar`,
        );
        const contatos = await response.json();
        document.getElementById("qntContatos").textContent = contatos.length
            ? contatos.length + " Contatos"
            : "Nenhum contato cadastrado";
        contatos.forEach((contato) => {
            const contatoElement = gerarCardContato(contato);
            contatoElement.className = "lista-contatos";
            document
                .getElementById("lista-contatos")
                .appendChild(contatoElement);
        });
    } catch (error) {
        alert("Erro ao buscar contatos: " + error.message);
    }
}

//Função assincrona que edita um contato especifico de um cliente especifico
async function editarContatoSalvar(evento) {
    evento.preventDefault();
    const tipo = document.getElementById("tipo-editar");
    const valor = document.getElementById("valor-editar");
    const observacao = document.getElementById("observacao-editar");
    const id = localStorage.getItem("idContatoSelecionado");

    await fetch(
        `http://localhost:8080/clientes/${cliente.id}/contatos/alterar`,
        {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                tipo: tipo.value,
                valor: valor.value,
                observacao: observacao.value,
                id: id,
            }),
        },
    )
        .then((response) => {
            if (response.status === 404) {
                throw new Error("Contato não encontrado");
            }
        })
        .then(() => {
            alert("Contado editado com sucesso");
            modalEditarContato.classList.add("hidden");
            buscarContatosCliente();
        })
        .catch((error) => {
            alert(error.message);
        });
}

//Função assincrona que edita um cliente especifico
async function editarClienteSalvar(evento) {
    evento.preventDefault();
    const nome = document.getElementById("nome-editar");
    const cpf = document.getElementById("cpf-input-modal-editar");
    const dataNascimento = document.getElementById("data_nascimento-editar");
    const endereco = document.getElementById("endereco-editar");
    const modal = document.getElementById("modal-editar");

    await fetch(`http://localhost:8080/clientes/alterar/${cliente.id}`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            nome: nome.value,
            cpf: cpf.value,
            data_nascimento: dataNascimento.value
                .split("-")
                .reverse()
                .join("/"),
            endereco: endereco.value,
        }),
    })
        .then((response) => {
            if (response.status === 404) {
                throw new Error("Cliente não encontrado.");
            }
        })
        .then(() => {
            alert("Cliente editado com sucesso!");
            window.location.href = "../index.html";
            modal.classList.add("hidden");
        })
        .catch((error) => {
            alert(error.message);
        });
}

//Função assincrona que exclui um cliente especifico
async function excluirCliente() {
    if (confirm("Tem certeza que deseja excluir este cliente?")) {
        try {
            await fetch(
                `http://localhost:8080/clientes/deletar/${cliente.id}`,
                {
                    method: "DELETE",
                },
            );
            alert("Cliente excluído com sucesso!");
            window.location.href = "../index.html";
        } catch (error) {
            console.error("Erro ao excluir cliente:", error);
        }
    }
}

//Função assincrona que cadastra um novo contato para um cliente especifico
async function cadastrarContato(evento) {
    evento.preventDefault();
    const tipo = document.getElementById("tipo");
    const valor = document.getElementById("valor");
    const observacao = document.getElementById("observacao");
    await fetch(`http://localhost:8080/clientes/${cliente.id}/contatos/criar`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            tipo: tipo.value,
            valor: valor.value,
            observacao: observacao.value,
        }),
    })
        .then((response) => {
            if (response.status === 409) {
                throw new Error("Telefone ou email ja cadastrados");
            }
        })
        .then(() => {
            alert("Contato cadastrado com sucesso!");
            limparDadosModalNovoContato();
            modalCriarContato.classList.add("hidden");
            buscarContatosCliente();
        })
        .catch((error) => {
            alert(error.message);
        });
}

//Função assincrona que exclui um contato especifico de um cliente especifico
async function excluirContato(id) {
    if (confirm("Tem certeza que deseja excluir este contato?")) {
        try {
            await fetch(
                `http://localhost:8080/clientes/${cliente.id}/contatos/deletar`,
                {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({
                        id: id,
                    }),
                },
            );
            alert("Contato excluído com sucesso!");
            buscarContatosCliente();
        } catch (error) {
            console.error("Erro ao excluir cliente:", error);
        }
    }
}

//Função que gera um card para mostrar as informações do contato
function gerarCardContato(contato) {
    const contatoElement = document.createElement("div");
    contatoElement.className = "lista-contatos";
    contatoElement.innerHTML = `
        <div class="contato-item">
            <div class="info-contato-container">
                <p class="label">Tipo de Contato:</p>
                <p class="value">${contato.tipo}</p>
            </div>

            <div class="info-contato-container">
                <p class="label">Valor do Contato:</p>
                <p class="value">${contato.valor}</p>
            </div>

            <div class="info-contato-container">
                <p class="label">Observação:</p>
                <p class="value">${contato.observacao || "Nenhuma observação"}</p>
            </div>
        </div>
        <div class="contato-actions">
            <button class="editar" onclick="editarContato(${contato.id}, '${contato.tipo}', '${contato.valor}', '${contato.observacao}')">Editar</button>
            <button class="excluir" onclick="excluirContato(${contato.id})">Excluir</button>
        </div>
    `;
    return contatoElement;
}

//Função que abre a modal de criar contato
function adicionarContato() {
    modalCriarContato.classList.remove("hidden");
}

//Função que abre a modal de editar contato com os dados do contato clicado
function editarContato(id, tipo, valor, observacao) {
    modalEditarContato.classList.remove("hidden");
    localStorage.removeItem("idContatoSelecionado");
    localStorage.setItem("idContatoSelecionado", id);
    document.getElementById("tipo-editar").value = tipo;
    document.getElementById("valor-editar").value = valor;
    document.getElementById("observacao-editar").value = observacao;
}

//Função que fecha a modal de editar contato
function cancelarEditarContato(evento) {
    evento.preventDefault();
    modalEditarContato.classList.add("hidden");
}

//Função que fecha a modal de criar um novo contato
function cancelarNovoContato(evento) {
    evento.preventDefault();
    limparDadosModalNovoContato();
    modalCriarContato.classList.add("hidden");
}

//Função que limpa os dado da modal de criar contato
function limparDadosModalNovoContato() {
    tipo.value = "Telefone";
    tipo.dispatchEvent(new Event("change"));
    observacao.value = "";

    const valor = document.getElementById("valor");
    valor.value = "";
}

//Evento que fica de olho se o usuário clicou fora de alguma modal, para fechar ela
window.addEventListener("click", (evento) => {
    if (evento.target === modalCriarContato) {
        evento.preventDefault();
        limparDadosModalNovoContato();
        modalCriarContato.classList.add("hidden");
    } else if (evento.target === modalEditar) {
        evento.preventDefault();
        modalEditar.classList.add("hidden");
    } else if (evento.target === modalEditarContato) {
        evento.preventDefault();
        modalEditarContato.classList.add("hidden");
    }
});

//Função que abre a modal de edição do cliente
function editarCliente() {
    modalEditar.classList.remove("hidden");
    document.getElementById("nome-editar").value = cliente.nome;
    document.getElementById("cpf-input-modal-editar").value = cliente.cpf;
    document.getElementById("data_nascimento-editar").value =
        cliente.data_nascimento
            .split("/") //Inverte o spliter
            .reverse()
            .join("-"); //Campo do tipo date so aceita - e não /
    document.getElementById("endereco-editar").value = cliente.endereco;
}

//Função que fecha a modal de edição do cliente
function cancelarEditarCliente(evento) {
    evento.preventDefault();
    modalEditar.classList.add("hidden");
}

//Executa a função que busca os contatos do cliente para mostrar eles
buscarContatosCliente();

//Coleta dos elementos necessários
const modal = document.getElementById("modal");
const modalEditar = document.getElementById("modal-editar");
const botaoNovoCliente = document.getElementById("btnNovoCliente");
const botaoCancelar = document.getElementById("cancelar");
const botaoCancelarEditar = document.getElementById("cancelar-editar");
const botaoSalvarEditar = document.getElementById("salvar-editar");
const nome = document.getElementById("nome");
const cpf = document.getElementById("cpf-input-modal");
const endereco = document.getElementById("endereco");
const nomeEditar = document.getElementById("nome-editar");
const cpfEditar = document.getElementById("cpf-input-modal-editar");
const dataNascimentoEditar = document.getElementById("data_nascimento-editar");
const enderecoEditar = document.getElementById("endereco-editar");

//Evento que fica de olho na data que vai ser escolhida na hora de criar um cliente, para verificar ela
const dataNascimento = document.getElementById("data_nascimento");
dataNascimento.addEventListener("change", () => {
    const dataSelecionada = new Date(dataNascimento.value);
    const hoje = new Date();
    const idadeMinima = 10;
    const dataLimite = new Date();
    dataLimite.setFullYear(hoje.getFullYear() - idadeMinima);
    if (dataSelecionada > hoje) {
        alert("A data não pode ser no futuro");
        dataNascimento.value = "";
    } else if (dataSelecionada > dataLimite) {
        alert("A pessoa deve ter pelo menos 10 anos");
        dataNascimento.value = "";
    }
});

//Função assincrona que cadastra um cliente
async function cadastrarCliente(evento) {
    evento.preventDefault();
    const nome = document.getElementById("nome");
    const cpf = document.getElementById("cpf-input-modal");
    const endereco = document.getElementById("endereco");
    const modal = document.getElementById("modal");
    if (cpf.value.length !== 14) {
        alert("Digite um CPF válido");
    } else {
        await fetch("http://localhost:8080/clientes/criar", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                nome: nome.value,
                cpf: cpf.value,
                data_nascimento: dataNascimento.value
                    .split("-") //Primeiro divide a data
                    .reverse() //Depois inverte o ano com o dia
                    .join("/"), //E junta tudo com /, gerando o formato esperado na API
                endereco: endereco.value,
            }),
        })
            .then((response) => {
                if (response.status === 409) {
                    throw new Error("CPF já cadastrado.");
                }
            })
            .then(() => {
                alert("Cliente cadastrado com sucesso!");
                limparCamposModalNovoCliente();
                modal.classList.add("hidden");
                buscarClientes();
            })
            .catch((error) => {
                alert(error.message);
            });
    }
}

//Função assincrona que salva a edição de um cliente
async function editarClienteSalvar(evento) {
    evento.preventDefault();

    const id = document.getElementById("salvar-editar").getAttribute("data-id");
    const nome = document.getElementById("nome-editar");
    const cpf = document.getElementById("cpf-input-modal-editar");
    const dataNascimento = document.getElementById("data_nascimento-editar");
    const endereco = document.getElementById("endereco-editar");
    const modal = document.getElementById("modal-editar");

    await fetch(`http://localhost:8080/clientes/alterar/${id}`, {
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
            limparCampoModalEditarCliente();
            modal.classList.add("hidden");
            buscarClientes();
        })
        .catch((error) => {
            alert(error.message);
        });
}

//Função assincrona que busca os clientes
async function buscarClientes() {
    try {
        const response = await fetch("http://localhost:8080/clientes/listar");
        const clientes = await response.json();
        document.querySelector(".lista-clientes-section").innerHTML = "";
        clientes.forEach((cliente) => {
            const clienteElement = gerarCardCliente(cliente);
            document
                .querySelector(".lista-clientes-section")
                .appendChild(clienteElement);
        });
    } catch (error) {
        console.error("Erro ao buscar clientes:", error);
    }
}

//Função assincrona que busca um cliente pelo cpf
async function buscarClientePorCPF(evento) {
    evento.preventDefault();
    const cpfInput = document.getElementById("cpf-input").value;
    if (cpfInput != "" && cpfInput.length === 14) {
        try {
            const response = await fetch(
                "http://localhost:8080/clientes/listarPorCPF",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ cpf: cpfInput }),
                },
            );
            if (response.status !== 404) {
                const cliente = await response.json();
                document.querySelector(".lista-clientes-section").innerHTML =
                    "";
                const clienteElement = gerarCardCliente(cliente);
                document
                    .querySelector(".lista-clientes-section")
                    .appendChild(clienteElement);
            } else {
                alert(
                    "Cliente não encontrado. Verifique o CPF e tente novamente.",
                );
            }
            document.getElementById("cpf-input").value = "";
        } catch (error) {
            console.error("Erro ao buscar cliente por CPF:", error);
            buscarClientes();
        }
    } else {
        alert("Por favor, insira um CPF válido para pesquisar.");
    }
}

//Função assincrona que exclui um cliente pelo id
async function excluirCliente(id) {
    if (confirm("Tem certeza que deseja excluir este cliente?")) {
        try {
            await fetch(`http://localhost:8080/clientes/deletar/${id}`, {
                method: "DELETE",
            });
            alert("Cliente excluído com sucesso!");
            buscarClientes();
        } catch (error) {
            console.error("Erro ao excluir cliente:", error);
        }
    }
}

//Função que gera os cards para as informações do cliente serem visualizadas
function gerarCardCliente(cliente) {
    const clienteElement = document.createElement("div");
    clienteElement.className = "cliente-container";
    clienteElement.innerHTML = `
        <div class="cliente-item">
            <p><strong>Nome:</strong> ${cliente.nome}</p>
            <p><strong>CPF:</strong> ${cliente.cpf}</p>
            <p><strong>Data de Nascimento:</strong> ${cliente.data_nascimento}</p>
            <p><strong>Endereço:</strong> ${cliente.endereco}</p>
        </div>
        <div class="cliente-actions">
            <button class="detalhes" onclick="detalhesCliente(${cliente.id}, '${cliente.nome}', '${cliente.cpf}', '${cliente.data_nascimento}', '${cliente.endereco}')">Detalhes</button>
            <button class="editar" onclick="editarCliente(${cliente.id}, '${cliente.nome}', '${cliente.cpf}', '${cliente.data_nascimento}', '${cliente.endereco}')">Editar</button>
            <button class="excluir" onclick="excluirCliente(${cliente.id})">Excluir</button>
        </div>
    `;
    return clienteElement;
}

//Fução que limpa os campos da modal que cria um cliente
function limparCamposModalNovoCliente() {
    nome.value = "";
    cpf.value = "";
    dataNascimento.value = "";
    endereco.value = "";
}

//Função que limpa os campos da modal que edita um cliente
function limparCampoModalEditarCliente() {
    nomeEditar.value = "";
    cpfEditar.value = "";
    dataNascimentoEditar.value = "";
    enderecoEditar.value = "";
}

//Função que fecha a modal que cria um cliente
function cancelarNovoCliente(evento) {
    evento.preventDefault();
    modal.classList.add("hidden");
    limparCamposModalNovoCliente();
}

//Função que abre a modal que cria um cliente
function abrirModalNovoCliente() {
    modal.classList.remove("hidden");
}

//Evento que fica de olho aonde o usuario clica quando a modal ta aberta, para fechar ela quando ele clicar fora dela
window.addEventListener("click", (evento) => {
    if (evento.target === modal) {
        evento.preventDefault();
        modal.classList.add("hidden");
        limparCamposModalNovoCliente();
    } else if (evento.target === modalEditar) {
        evento.preventDefault();
        modalEditar.classList.add("hidden");
        limparCampoModalEditarCliente();
    }
});

//Função que abre a modal que edita um cliente, ja com os dados especificos do cliente clicado
function editarCliente(id, nome, cpf, dataNascimento, endereco) {
    modalEditar.classList.remove("hidden");
    document.createAttribute("data-id");
    document.getElementById("salvar-editar").setAttribute("data-id", id);
    document.getElementById("nome-editar").value = nome;
    document.getElementById("cpf-input-modal-editar").value = cpf;
    document.getElementById("data_nascimento-editar").value = dataNascimento
        .split("/") //Inverte o spliter
        .reverse()
        .join("-"); //Campo do tipo date so aceita - e não /
    document.getElementById("endereco-editar").value = endereco;
}

//Função que fecha a modal que edita um cliente
function cancelarEditarCliente(evento) {
    evento.preventDefault();
    modalEditar.classList.add("hidden");
    limparCampoModalEditarCliente();
}

//Função que leva o usuário para a página de detalhes do cliente, salvando esse cliente em um armazenamento local para ser pego na outra página
function detalhesCliente(id, nome, cpf, dataNascimento, endereco) {
    const cliente = {
        id: id,
        nome: nome,
        cpf: cpf,
        data_nascimento: dataNascimento,
        endereco: endereco,
    };
    localStorage.removeItem("clienteSelecionado");
    localStorage.setItem("clienteSelecionado", JSON.stringify(cliente));
    window.location.href = "./telaDetalhes/detalhes.html";
}

//Comentários com foco na aprendizagem, explicando o que cada parte do código faz e a lógica por trás dela pq regex é difícil
//Formata do campo CPF pro formato desejado (XXX.XXX.XXX-XX)
const cpfInput = document.getElementById("cpf-input");
cpfInput.addEventListener("input", (evento) => {
    let value = evento.target.value;
    value = value.replace(/\D/g, ""); //Remove tudo que não for número, \D = qlqr coisa que não é número, g = global, remove tudo

    //Adiciona o primeiro ponto após os primeiros 3 dígitos,
    // (\d{3}) = grupo 1 de 3 dígitos,
    // (\d) = grupo 2, $1.$2 = substitui o dígito do grupo 1 seguido de um ponto e depois o dígito do grupo 2
    value = value.replace(/(\d{3})(\d)/, "$1.$2");

    value = value.replace(/(\d{3})(\d)/, "$1.$2"); //Adiciona o segundo ponto após os próximos 3 dígitos, mesma lógica de cima

    //Adiciona o hífen antes dos últimos 2 dígitos, parcial mesma lógica,
    // (\d{1,2}) = grupo 2 de 1 ou 2 dígitos,
    // $1-$2 = substitui o dígito do grupo 1 seguido de um hífen e depois o dígito do grupo 2
    value = value.replace(/(\d{3})(\d{1,2})/, "$1-$2");
    evento.target.value = value;
});

//Mesma lógica do campo de pesquisa, mas pro modal de cadastro
const cpfInputModal = document.getElementById("cpf-input-modal");
cpfInputModal.addEventListener("input", (evento) => {
    let value = evento.target.value;
    value = value.replace(/\D/g, "");
    value = value.replace(/(\d{3})(\d)/, "$1.$2");
    value = value.replace(/(\d{3})(\d)/, "$1.$2");
    value = value.replace(/(\d{3})(\d{1,2})/, "$1-$2");
    evento.target.value = value;
});

//Mesma lógica do campo de pesquisa, mas pro modal de cadastro
const cpfInputModalEditar = document.getElementById("cpf-input-modal-editar");
cpfInputModalEditar.addEventListener("input", (evento) => {
    let value = evento.target.value;
    value = value.replace(/\D/g, "");
    value = value.replace(/(\d{3})(\d)/, "$1.$2");
    value = value.replace(/(\d{3})(\d)/, "$1.$2");
    value = value.replace(/(\d{3})(\d{1,2})/, "$1-$2");
    evento.target.value = value;
});

//Mostrar os clientes quando é executado
buscarClientes();

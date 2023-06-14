export const formatedDate2PtBR = () =>
  new Date().toLocaleDateString("pt-br") + 1;

export const formatedMonth2PtBR = () =>
  new Date().toLocaleDateString("pt-br", { month: "2-digit" });

export const formatedYear2Ptbr = () =>
  new Date().toLocaleDateString("pt-br", { year: "numeric" });

export function obterDataAmanha() {
  var hoje = new Date();
  var amanha = new Date(hoje.getTime() + 24 * 60 * 60 * 1000);

  var dia = amanha.getDate().toString().padStart(2, "0");
  var mes = (amanha.getMonth() + 1).toString().padStart(2, "0");
  var ano = amanha.getFullYear().toString();

  return dia + "/" + mes + "/" + ano;
}

export function obterHorarioAleatorio() {
  var inicio = new Date();  // Obtém a data e hora atual
  inicio.setHours(8, 0, 0, 0);  // Define o horário de início para 08:00

  var fim = new Date();  // Obtém a data e hora atual novamente
  fim.setHours(18, 0, 0, 0);  // Define o horário de fim para 18:00

  var horarioAleatorio = new Date(inicio.getTime() + Math.random() * (fim.getTime() - inicio.getTime()));

  var horas = horarioAleatorio.getHours().toString().padStart(2, '0');  // Obtém as horas e adiciona um zero à esquerda se necessário
  var minutos = horarioAleatorio.getMinutes().toString().padStart(2, '0');  // Obtém os minutos e adiciona um zero à esquerda se necessário

  return horas + ':' + minutos;
}

export function obterNumeroAleatorio() {
  var numeroAleatorio = Math.floor(Math.random() * 10);
  return numeroAleatorio;
}

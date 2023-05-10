export const formatedDate2PtBR = () => new Date().toLocaleDateString("pt-br") + 1;

export const formatedMonth2PtBR = () => new Date().toLocaleDateString("pt-br", { month: "2-digit" });

export const formatedYear2Ptbr = () => new Date().toLocaleDateString("pt-br", { year: "numeric" });

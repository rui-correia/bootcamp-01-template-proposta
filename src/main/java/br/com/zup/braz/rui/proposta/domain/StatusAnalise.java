package br.com.zup.braz.rui.proposta.domain;

public enum StatusAnalise {

    COM_RESTRICAO {
        @Override
        public StatusProposta toPropostaStatus(){
            return StatusProposta.NAO_ELEGIVEL;
        }
    },
    SEM_RESTRICAO {
        @Override
        public StatusProposta toPropostaStatus() {
            return StatusProposta.ELEGIVEL;
        }
    };

    public abstract StatusProposta toPropostaStatus();
}

package one.digitalInnovation.desafio_gft.service.impl;

import one.digitalInnovation.desafio_gft.model.Cliente;
import one.digitalInnovation.desafio_gft.model.ClienteRepository;
import one.digitalInnovation.desafio_gft.model.Endereco;
import one.digitalInnovation.desafio_gft.model.EnderecoRepository;
import one.digitalInnovation.desafio_gft.service.ClienteService;
import one.digitalInnovation.desafio_gft.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteServiceImpl implements ClienteService {

    //Singleton: Injetar componetntes Autowired
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;


    @Override
    public Iterable<Cliente> buscarTodos() {
        //Buscar todos os clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        //Buscar por ID
        Optional<Cliente> cliente =  clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        // Verificar se o endereço ja existe
        // Caso não exista, integrar no via CEP
        // Inserir cliente vinculado ao endereço

        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {

            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;

        });

        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Buscar cliente por ID caso exista
        // Verificar se o endereço ja existe (pelo cep)
        // Caso não exista, Integrar no Viacep
        // Alterar cliente vinculado ( novo ou existente )

        Optional <Cliente> clienteBD =  clienteRepository.findById(id);
        if( clienteBD.isPresent()){

            String cep = cliente.getEndereco().getCep();
            Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {

                Endereco novoEndereco = viaCepService.consultarCep(cep);
                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            });
        }

    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);

    }
}

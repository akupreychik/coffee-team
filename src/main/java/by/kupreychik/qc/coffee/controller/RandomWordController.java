package by.kupreychik.qc.coffee.controller;

import by.kupreychik.qc.coffee.dto.error.ErrorDTO;
import by.kupreychik.qc.coffee.enums.Types;
import by.kupreychik.qc.coffee.model.Phrase;
import by.kupreychik.qc.coffee.service.WordsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Tag(name = "public", description = "Words controller")
public class RandomWordController {

    @Autowired
    private WordsService wordsService;

    @GetMapping("/public/randomWord")
    @Operation(summary = "Generates random word",
            tags = {"public"},
            description = "Returns random word from file phrases.txt",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            schema = @Schema(implementation = Phrase.class)
                    )),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
                            schema = @Schema(implementation = ErrorDTO.class)
                    ))
            })
    public ModelAndView getRandom() {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("random", wordsService.getRandomWord(Types.RANDOM));
        return modelAndView;
    }


}

package by.kupreychik.qc.coffee.controller;

import by.kupreychik.qc.coffee.command.AddNewWordCommand;
import by.kupreychik.qc.coffee.dto.error.ErrorDTO;
import by.kupreychik.qc.coffee.enums.Types;
import by.kupreychik.qc.coffee.model.Phrase;
import by.kupreychik.qc.coffee.service.WordsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Tag(name = "words", description = "Words controller")
public class MyWordController {

    private final WordsService wordsService;

    public MyWordController(WordsService wordsService) {
        this.wordsService = wordsService;

    }

    @GetMapping("/my/randomWord")
    @Operation(summary = "Generates random word",
            tags = {"words"},
            description = "Returns random word from file phrases.txt",
            responses = {
                    @ApiResponse(description = "The word", content = @Content(
                            schema = @Schema(implementation = Phrase.class)
                    )),
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    public ModelAndView getMyRandom() {
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("random", wordsService.getRandomWord(Types.ME));
        return modelAndView;
    }

    @PostMapping("/my/randomWord")
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
    public ResponseEntity<String> addNewWord(@RequestBody AddNewWordCommand word) {
        wordsService.addWord(word.getWord());
        return ResponseEntity.ok().build();
    }
}

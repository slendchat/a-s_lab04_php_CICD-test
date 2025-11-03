<?php

use PHPUnit\Framework\TestCase;

class PlaceImagesTest extends TestCase
{
    public function test_placeImages_outputs_img_tags_for_jpgs(): void
    {
        // Include the file to load the function, discard page output
        ob_start();
        include __DIR__ . '/../index.php';
        ob_end_clean();

        $dir = './image/';

        // Capture only the output from the function call we make
        ob_start();
        placeImages($dir);
        $output = ob_get_clean();

        $this->assertNotEmpty($output, 'placeImages should output content.');

        $files = scandir($dir);
        $jpgs = array_filter($files ?: [], function ($f) {
            return $f !== '.' && $f !== '..' && pathinfo($f, PATHINFO_EXTENSION) === 'jpg';
        });

        $this->assertNotEmpty($jpgs, 'Expected jpg images in image directory for test.');

        foreach ($jpgs as $file) {
            $expected = "<img src='{$dir}{$file}' alt='Cat'>";
            $this->assertStringContainsString($expected, $output, "Missing expected img tag for {$file}");
        }
    }
}

